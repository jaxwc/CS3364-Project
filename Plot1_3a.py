import matplotlib.pyplot as plt
import pandas as pd

df = pd.read_csv("benchmark_random.csv")

best = df.loc[df.groupby("n")["avg_time_ms"].idxmin()]

plt.figure(figsize=(7, 5))
plt.plot(best["n"], best["k"], marker="o", linewidth=2)
plt.xlabel("n (array size)")
plt.ylabel("Optimal K")
plt.title("Optimal K vs n (random input)")
plt.grid(True, alpha=0.3)
plt.savefig("deliverable1_3a.png", dpi=150, bbox_inches="tight")
plt.show()
