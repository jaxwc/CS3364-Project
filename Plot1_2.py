import matplotlib.pyplot as plt
import pandas as pd

df = pd.read_csv("benchmark_random.csv")

plt.figure(figsize=(8, 5))
for n, group in df.groupby("n"):
    plt.plot(group["k"], group["avg_time_ms"], marker="o", label=f"n={n}")

plt.xlabel("K (insertion-sort threshold)")
plt.ylabel("Average runtime (ms)")
plt.title("QuickHybridSort runtimes vs K (random input, 20 trials)")
plt.legend()
plt.grid(True, alpha=0.3)
plt.savefig("deliverable1_2.png", dpi=150, bbox_inches="tight")
